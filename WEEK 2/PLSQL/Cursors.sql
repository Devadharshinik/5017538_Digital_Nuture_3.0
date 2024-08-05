-- Scenario 1: GenerateMonthlyStatements
DECLARE
    CURSOR c_transactions IS
        SELECT customer_id, transaction_date, transaction_amount, transaction_type
        FROM Transactions
        WHERE EXTRACT(MONTH FROM transaction_date) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM transaction_date) = EXTRACT(YEAR FROM SYSDATE);
    
    v_customer_id Transactions.customer_id%TYPE;
    v_transaction_date Transactions.transaction_date%TYPE;
    v_transaction_amount Transactions.transaction_amount%TYPE;
    v_transaction_type Transactions.transaction_type%TYPE;

BEGIN
    OPEN c_transactions;
    
    LOOP
        FETCH c_transactions INTO v_customer_id, v_transaction_date, v_transaction_amount, v_transaction_type;
        
        EXIT WHEN c_transactions%NOTFOUND;
        
        -- Print or process the transaction statement
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_customer_id);
        DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || v_transaction_date);
        DBMS_OUTPUT.PUT_LINE('Transaction Amount: ' || v_transaction_amount);
        DBMS_OUTPUT.PUT_LINE('Transaction Type: ' || v_transaction_type);
        DBMS_OUTPUT.PUT_LINE('----------------------------------------');
    END LOOP;
    
    CLOSE c_transactions;
END;
/


-- Scenario 2: ApplyAnnualFee
DECLARE
    CURSOR c_accounts IS
        SELECT account_id, balance
        FROM accounts;
    
    v_account_id accounts.account_id%TYPE;
    v_balance accounts.balance%TYPE;
    v_fee NUMBER := 50; -- Annual maintenance fee
    
BEGIN
    OPEN c_accounts;
    
    LOOP
        FETCH c_accounts INTO v_account_id, v_balance;
        
        EXIT WHEN c_accounts%NOTFOUND;
        
        -- Deduct the annual maintenance fee from the balance
        UPDATE accounts
        SET balance = v_balance - v_fee
        WHERE account_id = v_account_id;
        
        DBMS_OUTPUT.PUT_LINE('Account ID: ' || v_account_id || ' - Annual fee of ' || v_fee || ' applied.');
    END LOOP;
    
    COMMIT;
    CLOSE c_accounts;
END;
/



-- Scenario 3: UpdateLoanInterestRates
DECLARE
    CURSOR c_loans IS
        SELECT loan_id, current_interest_rate
        FROM loans;
    
    v_loan_id loans.loan_id%TYPE;
    v_current_interest_rate loans.current_interest_rate%TYPE;
    v_new_interest_rate NUMBER;
    
BEGIN
    OPEN c_loans;
    
    LOOP
        FETCH c_loans INTO v_loan_id, v_current_interest_rate;
        
        EXIT WHEN c_loans%NOTFOUND;
        
        -- Calculate the new interest rate based on the new policy
        v_new_interest_rate := v_current_interest_rate + 0.02; -- Example policy: increase by 2%
        
        -- Update the loan interest rate
        UPDATE loans
        SET current_interest_rate = v_new_interest_rate
        WHERE loan_id = v_loan_id;
        
        DBMS_OUTPUT.PUT_LINE('Loan ID: ' || v_loan_id || ' - Interest rate updated to ' || v_new_interest_rate);
    END LOOP;
    
    COMMIT;
    CLOSE c_loans;
END;
/





