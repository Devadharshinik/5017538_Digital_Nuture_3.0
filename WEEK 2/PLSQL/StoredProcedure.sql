-- Scenario 1: ProcessMonthlyInterest Procedure
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    -- Update the balance of all savings accounts
    UPDATE accounts
    SET balance = balance * 1.01
    WHERE account_type = 'Savings';

    -- Commit the transaction
    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to all savings accounts.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error processing monthly interest: ' || SQLERRM);
END ProcessMonthlyInterest;
/



-- Scenario 2: UpdateEmployeeBonus Procedure
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department_id IN NUMBER,
    p_bonus_percentage IN NUMBER
) IS
BEGIN
    -- Update the salary with the bonus percentage
    UPDATE employees
    SET salary = salary * (1 + p_bonus_percentage / 100)
    WHERE department_id = p_department_id;

    -- Check if any rows were updated
    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found for the given department ID.');
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Bonus updated for employees in department ID: ' || p_department_id);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonus: ' || SQLERRM);
END UpdateEmployeeBonus;
/


-- Scenario 3: TransferFunds Procedure
CREATE OR REPLACE PROCEDURE TransferFunds(
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
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in source account.');
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

    DBMS_OUTPUT.PUT_LINE('Funds transferred successfully.');
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK TO SAVEPOINT before_transfer;
        DBMS_OUTPUT.PUT_LINE('Error: One or both accounts do not exist.');
    WHEN OTHERS THEN
        ROLLBACK TO SAVEPOINT before_transfer;
        DBMS_OUTPUT.PUT_LINE('Error during fund transfer: ' || SQLERRM);
END TransferFunds;
/


