CREATE TABLE transaction (
    transaction_id NUMBER(19,0) PRIMARY KEY,
    account_id NUMBER(19,0) NOT NULL,
    transaction_date DATE NOT NULL,
    amount NUMBER(10,2) NOT NULL,
    transaction_type VARCHAR2(20) NOT NULL,
    status VARCHAR2(20) NOT NULL,
    currency VARCHAR2(10) NOT NULL,
    location VARCHAR2(50) NOT NULL
);

SELECT COUNT(*) FROM transaction;


-- 1. Truy vấn theo account_id
SELECT * FROM transaction WHERE account_id = 12345;

-- 2. Truy vấn theo status
SELECT * FROM transaction WHERE status = 'success';

-- 3. Truy vấn theo transaction_type + status
SELECT * FROM transaction WHERE transaction_type = 'deposit' AND status = 'pending';

-- 4. Truy vấn theo ngày
SELECT * FROM transaction WHERE transaction_date BETWEEN TO_DATE('2025-05-10', 'YYYY-MM-DD') AND TO_DATE('2025-05-20', 'YYYY-MM-DD');

-- 5. Sắp xếp theo amount
SELECT * FROM transaction ORDER BY amount DESC;

-- 6. Thống kê tổng số tiền theo status
SELECT status, SUM(amount) FROM transaction GROUP BY status;


-- Tạo index
CREATE INDEX idx_account_id ON transaction(account_id);
CREATE INDEX idx_status ON transaction(status);
CREATE INDEX idx_transaction_type ON transaction(transaction_type);
CREATE INDEX idx_transaction_date ON transaction(transaction_date);
CREATE INDEX idx_amount ON transaction(amount);
CREATE INDEX idx_currency ON transaction(currency);
CREATE INDEX idx_location ON transaction(location);
CREATE INDEX idx_multi ON transaction(account_id, transaction_date, status, amount, currency);

-- 1. Truy vấn toàn bộ transaction theo tài khoản
SELECT * FROM transaction WHERE account_id = 12345;

-- 2. Truy vấn theo trạng thái giao dịch
SELECT * FROM transaction WHERE status = 'success';


-- 3. Số lượng giao dịch theo status
SELECT status, COUNT(*) as transaction_count
FROM transaction
GROUP BY status;


-- 4. Truy vấn theo loại giao dịch và số tiền lớn
SELECT * FROM transaction
WHERE transaction_type = 'withdrawal' AND amount > 10000;

-- 5. Truy vấn kết hợp nhiều cột
SELECT * FROM transaction
WHERE account_id = 12345
AND transaction_date BETWEEN TO_DATE('2025-05-01', 'YYYY-MM-DD') AND TO_DATE('2025-05-31', 'YYYY-MM-DD')
AND status = 'success';

-- 6. Phân tích giao dịch theo thời gian, tài khoản và trạng thái
SELECT TO_CHAR(transaction_date, 'YYYY-MM-DD'), COUNT(*) as transaction_count
FROM transaction
WHERE account_id = 12345
AND status = 'success'
AND transaction_date BETWEEN TO_DATE('2025-05-01', 'YYYY-MM-DD') AND TO_DATE('2025-05-31', 'YYYY-MM-DD')
GROUP BY TO_CHAR(transaction_date, 'YYYY-MM-DD');

-- 7. Thống kê số lượng giao dịch theo vị trí và loại giao dịch
SELECT location, transaction_type, COUNT(*) as transaction_count
FROM transaction
GROUP BY location, transaction_type;

-- 8. Giao dịch số tiền lớn, USD, trạng thái success
SELECT * FROM transaction
WHERE amount > 10000
AND currency = 'USD'
AND status = 'success';

-- 9. Phân tích nhiều tài khoản cùng lúc
SELECT account_id, status, COUNT(*) as transaction_count
FROM transaction
WHERE account_id IN (12345, 12346, 12347)
AND transaction_date BETWEEN TO_DATE('2025-05-01', 'YYYY-MM-DD') AND TO_DATE('2025-05-31', 'YYYY-MM-DD')
GROUP BY account_id, status;

-- 10. Giao dịch tìm kiếm đa chiều
SELECT * FROM transaction
WHERE account_id = 12345
AND transaction_date BETWEEN TO_DATE('2025-05-01', 'YYYY-MM-DD') AND TO_DATE('2025-05-31', 'YYYY-MM-DD')
AND status = 'success'
AND amount > 500
AND currency = 'EUR';



