import oracledb
from faker import Faker
import random
from datetime import datetime
# Khởi tạo Faker
fake = Faker()
# Kết nối Oracle (thin mode)
connection = oracledb.connect(
    user="sys",
    password="HonKit1216",
    dsn="localhost:1522/XEPDB1",
    mode=oracledb.AUTH_MODE_SYSDBA
)
cursor = connection.cursor()
# Tham số
num_records = 6900000  # Thêm 6.9 triệu bản ghi
start_id = 100001      # Bắt đầu từ ID kế tiếp
num_accounts = 7
account_ids = [12345 + i for i in range(num_accounts)]
start_date = datetime(2025, 5, 5)
end_date = datetime(2025, 6, 2)
transaction_types = ['deposit', 'withdrawal', 'transfer']
statuses = ['success', 'failed', 'pending']
currencies = ['USD', 'EUR', 'GBP']
locations = ['New York', 'London', 'Tokyo', 'Sydney', 'Paris']
# Chèn dữ liệu theo batch
batch = []
batch_size = 10000
for i in range(num_records):
    batch.append((
        start_id + i,
        random.choice(account_ids),
        fake.date_time_between(start_date=start_date, end_date=end_date),
        round(random.uniform(10.0, 50000.0), 2),
        random.choice(transaction_types),
        random.choice(statuses),
        random.choice(currencies),
        random.choice(locations)
    ))
if len(batch) >= batch_size:
    cursor.executemany("""
            INSERT INTO transaction (transaction_id, account_id, transaction_date, amount, transaction_type, status, currency, location)
            VALUES (:1, :2, :3, :4, :5, :6, :7, :8)
        """, batch)
    connection.commit()
    print(f"Đã chèn {start_id + i} bản ghi")
    batch = []
# Chèn các bản ghi còn lại
if batch:
    cursor.executemany("""
        INSERT INTO transaction (transaction_id, account_id, transaction_date, amount, transaction_type, status, currency, location)
        VALUES (:1, :2, :3, :4, :5, :6, :7, :8)
    """, batch)
    connection.commit()
    print(f"Đã chèn {start_id + num_records - 1} bản ghi")
# Kết thúc
cursor.close()
connection.close()
print("Hoàn tất chèn 6.9 triệu bản ghi.")
