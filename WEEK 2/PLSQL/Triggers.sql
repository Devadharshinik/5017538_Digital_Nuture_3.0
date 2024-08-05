-- Scenario 1: UpdateCustomerLastModified Trigger
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END UpdateCustomerLastModified;
/


-- Scenario 2: LogTransaction Trigger
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (
        audit_id,            -- Assuming you have an auto-incrementing primary key
        transaction_id,
        account_id,
        transaction_type,
        amount,
        transaction_date,
        log_date
    ) VALUES (
        AuditLog_seq.NEXTVAL, -- Assuming you have a sequence for the audit ID
        :NEW.transaction_id,
        :NEW.account_id,
        :NEW.transaction_type,
        :NEW.amount,
        :NEW.transaction_date,
        SYSDATE
    );
END LogTransaction;
/


-- Scenario 3: CheckTransactionRules Trigger
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    -- Check if the transaction type is a withdrawal
    IF :NEW.transaction_type = 'Withdrawal' THEN
        -- Retrieve the current balance of the account
        SELECT balance INTO v_balance
        FROM accounts
        WHERE account_id = :NEW.account_id;
        
        -- Ensure the withdrawal amount does not exceed the balance
        IF :NEW.amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds for withdrawal.');
        END IF;
        
    -- Check if the transaction type is a deposit
    ELSIF :NEW.transaction_type = 'Deposit' THEN
        -- Ensure the deposit amount is positive
        IF :NEW.amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
        END IF;
    ELSE
        RAISE_APPLICATION_ERROR(-20003, 'Invalid transaction type.');
    END IF;
END CheckTransactionRules;
/





