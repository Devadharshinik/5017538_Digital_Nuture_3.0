-- Scenario 1: CustomerManagement Package
-- Package Specification (CustomerManagement.pks):
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_dob IN DATE,
        p_balance IN NUMBER
    );
    
    PROCEDURE UpdateCustomer(
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_dob IN DATE,
        p_balance IN NUMBER
    );
    
    FUNCTION GetCustomerBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/

-- Package Body (CustomerManagement.pkb):
CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    
    PROCEDURE AddCustomer(
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_dob IN DATE,
        p_balance IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Customers (customer_id, name, dob, balance)
        VALUES (p_customer_id, p_name, p_dob, p_balance);
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error adding customer: ' || SQLERRM);
    END AddCustomer;
    
    PROCEDURE UpdateCustomer(
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_dob IN DATE,
        p_balance IN NUMBER
    ) IS
    BEGIN
        UPDATE Customers
        SET name = p_name, dob = p_dob, balance = p_balance
        WHERE customer_id = p_customer_id;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error updating customer: ' || SQLERRM);
    END UpdateCustomer;
    
    FUNCTION GetCustomerBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER IS
        v_balance NUMBER;
    BEGIN
        SELECT balance INTO v_balance
        FROM Customers
        WHERE customer_id = p_customer_id;
        
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error fetching customer balance: ' || SQLERRM);
            RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/


-- Scenario 2: EmployeeManagement Package
-- Package Specification (EmployeeManagement.pks):

CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_employee_id IN NUMBER,
        p_name IN VARCHAR2,
        p_position IN VARCHAR2,
        p_salary IN NUMBER
    );
    
    PROCEDURE UpdateEmployee(
        p_employee_id IN NUMBER,
        p_name IN VARCHAR2,
        p_position IN VARCHAR2,
        p_salary IN NUMBER
    );
    
    FUNCTION CalculateAnnualSalary(
        p_employee_id IN NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/

-- Package Body (EmployeeManagement.pkb):
CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    
    PROCEDURE HireEmployee(
        p_employee_id IN NUMBER,
        p_name IN VARCHAR2,
        p_position IN VARCHAR2,
        p_salary IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Employees (employee_id, name, position, salary)
        VALUES (p_employee_id, p_name, p_position, p_salary);
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error hiring employee: ' || SQLERRM);
    END HireEmployee;
    
    PROCEDURE UpdateEmployee(
        p_employee_id IN NUMBER,
        p_name IN VARCHAR2,
        p_position IN VARCHAR2,
        p_salary IN NUMBER
    ) IS
    BEGIN
        UPDATE Employees
        SET name = p_name, position = p_position, salary = p_salary
        WHERE employee_id = p_employee_id;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error updating employee: ' || SQLERRM);
    END UpdateEmployee;
    
    FUNCTION CalculateAnnualSalary(
        p_employee_id IN NUMBER
    ) RETURN NUMBER IS
        v_salary NUMBER;
    BEGIN
        SELECT salary INTO v_salary
        FROM Employees
        WHERE employee_id = p_employee_id;
        
        RETURN v_salary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error calculating annual salary: ' || SQLERRM);
            RETURN NULL;
    END CalculateAnnualSalary;

END EmployeeManagement;
/


-- Scenario 3: AccountOperations Package
-- Package Specification (AccountOperations.pks):
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(
        p_account_id IN NUMBER,
        p_customer_id IN NUMBER,
        p_initial_balance IN NUMBER
    );
    
    PROCEDURE CloseAccount(
        p_account_id IN NUMBER
    );
    
    FUNCTION GetTotalBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/

-- Package Body (AccountOperations.pkb):
CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    
    PROCEDURE OpenAccount(
        p_account_id IN NUMBER,
        p_customer_id IN NUMBER,
        p_initial_balance IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Accounts (account_id, customer_id, balance)
        VALUES (p_account_id, p_customer_id, p_initial_balance);
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error opening account: ' || SQLERRM);
    END OpenAccount;
    
    PROCEDURE CloseAccount(
        p_account_id IN NUMBER
    ) IS
    BEGIN
        DELETE FROM Accounts
        WHERE account_id = p_account_id;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error closing account: ' || SQLERRM);
    END CloseAccount;
    
    FUNCTION GetTotalBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER IS
        v_total_balance NUMBER;
    BEGIN
        SELECT SUM(balance) INTO v_total_balance
        FROM Accounts
        WHERE customer_id = p_customer_id;
        
        RETURN NVL(v_total_balance, 0); -- Handle case where there are no accounts
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error fetching total balance: ' || SQLERRM);
            RETURN NULL;
    END GetTotalBalance;

END AccountOperations;
/









