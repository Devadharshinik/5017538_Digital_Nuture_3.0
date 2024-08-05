-- Scenario 1: SafeTransferFunds Procedure
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_account IN NUMBER,
    p_to_account IN NUMBER,
    p_amount IN NUMBER
) AS
    v_balance NUMBER;
BEGIN
    -- Start a transaction
    SAVEPOINT before_transfer;
    
    -- Check if the source account has enough balance
    SELECT balance INTO v_balance
    FROM accounts
    WHERE account_id = p_from_account;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds.');
    END IF;

    -- Deduct funds from the source account
    UPDATE accounts
    SET balance = balance - p_amount
    WHERE account_id = p_from_account;

    -- Add funds to the destination account
    UPDATE accounts
    SET balance = balance + p_amount
    WHERE account_id = p_to_account;

    -- Commit the transaction
    COMMIT;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK TO SAVEPOINT before_transfer;
        DBMS_OUTPUT.PUT_LINE('Error: Account does not exist.');
    WHEN OTHERS THEN
        ROLLBACK TO SAVEPOINT before_transfer;
        DBMS_OUTPUT.PUT_LINE('Error during fund transfer: ' || SQLERRM);
END SafeTransferFunds;
/



-- Scenario 2: UpdateSalary Procedure
CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id IN NUMBER,
    p_percentage IN NUMBER
) AS
BEGIN
    -- Update the salary by the given percentage
    UPDATE employees
    SET salary = salary * (1 + p_percentage / 100)
    WHERE employee_id = p_employee_id;

    -- Check if any rows were updated
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Employee ID does not exist.');
    END IF;

    -- Commit the transaction
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating salary: ' || SQLERRM);
END UpdateSalary;
/



-- Scenario 3: AddNewCustomer Procedure
CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_email IN VARCHAR2
) AS
BEGIN
    -- Attempt to insert a new customer
    INSERT INTO customers (customer_id, name, email)
    VALUES (p_customer_id, p_name, p_email);

    -- Commit the transaction
    COMMIT;

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Customer with this ID already exists.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error adding new customer: ' || SQLERRM);
END AddNewCustomer;
/


