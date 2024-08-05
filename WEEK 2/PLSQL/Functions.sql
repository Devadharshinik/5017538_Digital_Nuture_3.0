-- Scenario 1: CalculateAge Function
CREATE OR REPLACE FUNCTION CalculateAge(
    p_dob DATE
) RETURN NUMBER IS
    v_age NUMBER;
BEGIN
    -- Calculate age
    SELECT FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12) INTO v_age FROM dual;
    RETURN v_age;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error calculating age: ' || SQLERRM);
        RETURN NULL;
END CalculateAge;
/




-- Scenario 2: CalculateMonthlyInstallment Function
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount NUMBER,
    p_annual_interest_rate NUMBER,
    p_duration_years NUMBER
) RETURN NUMBER IS
    v_monthly_installment NUMBER;
    v_monthly_rate NUMBER;
    v_num_payments NUMBER;
BEGIN
    -- Convert annual interest rate to monthly and calculate number of payments
    v_monthly_rate := p_annual_interest_rate / 1200;
    v_num_payments := p_duration_years * 12;

    -- Calculate the monthly installment using the formula
    IF v_monthly_rate > 0 THEN
        v_monthly_installment := p_loan_amount * v_monthly_rate / (1 - POWER(1 + v_monthly_rate, -v_num_payments));
    ELSE
        v_monthly_installment := p_loan_amount / v_num_payments;
    END IF;

    RETURN v_monthly_installment;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error calculating monthly installment: ' || SQLERRM);
        RETURN NULL;
END CalculateMonthlyInstallment;
/



-- Scenario 3: HasSufficientBalance Function
CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id NUMBER,
    p_amount NUMBER
) RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    -- Retrieve the current balance for the account
    SELECT balance INTO v_balance
    FROM accounts
    WHERE account_id = p_account_id;

    -- Check if the balance is sufficient
    IF v_balance >= p_amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Account does not exist.');
        RETURN FALSE;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error checking balance: ' || SQLERRM);
        RETURN FALSE;
END HasSufficientBalance;
/


