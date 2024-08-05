-- Scenario 1: Applying a Discount to Loan Interest Rates for Customers Above 60 Years Old
DECLARE
    CURSOR c_customers IS
        SELECT customer_id, loan_interest_rate, age
        FROM customers
        JOIN loans ON customers.customer_id = loans.customer_id
        WHERE age > 60;
    v_new_interest_rate NUMBER;
BEGIN
    FOR rec IN c_customers LOOP
        v_new_interest_rate := rec.loan_interest_rate - 0.01; -- Applying 1% discount
        UPDATE loans
        SET loan_interest_rate = v_new_interest_rate
        WHERE customer_id = rec.customer_id;
    END LOOP;

    COMMIT; -- Commit changes to the database
    DBMS_OUTPUT.PUT_LINE('Discounts applied to eligible customers.');
END;
/




-- Scenario 2: Promoting Customers to VIP Status Based on Balance
DECLARE
    CURSOR c_customers IS
        SELECT customer_id, balance
        FROM customers
        WHERE balance > 10000;
BEGIN
    FOR rec IN c_customers LOOP
        UPDATE customers
        SET IsVIP = TRUE
        WHERE customer_id = rec.customer_id;
    END LOOP;

    COMMIT; -- Commit changes to the database
    DBMS_OUTPUT.PUT_LINE('VIP status updated for eligible customers.');
END;
/


-- Scenario 3: Sending Reminders for Loans Due Within the Next 30 Days
DECLARE
    CURSOR c_loans IS
        SELECT customer_id, loan_due_date
        FROM loans
        WHERE loan_due_date BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    FOR rec IN c_loans LOOP
        -- Assuming you have a procedure or function to send reminders
        -- Example: send_reminder(rec.customer_id, rec.loan_due_date);
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan due on ' || rec.loan_due_date || ' for customer ID: ' || rec.customer_id);
    END LOOP;
END;
/




