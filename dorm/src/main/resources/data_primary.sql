-- 记录一下添加密码加密算法之后的模拟数据插入

-- 插入初始管理员账户
INSERT INTO users (student_id, password, name, gender, email, phone, role) VALUES
    ('admin', 'admin123', 'headmaster', 'MALE', 'headmaster@cuhk.edu.cn', '13800138000', 'ADMIN');

-- 插入初始学生账户
INSERT INTO users (student_id, password, name, gender, email, phone, role) VALUES
                                                                               ('***', 'iAmAuthor', '***', 'MALE', '***@link.cuhk.edu.cn', '13700000000', 'STUDENT'),
                                                                               ('123456789', 'student123', '张三', 'MALE', 'zhangsan@link.cuhk.edu.cn', '13800138001', 'STUDENT'),
                                                                               ('123456790', 'student123', '李四', 'FEMALE', 'lisi@link.cuhk.edu.cn', '13800138002', 'STUDENT'),
                                                                               ('123456791', 'student123', '王五', 'FEMALE', 'wangwu@link.cuhk.edu.cn', '13800138003', 'STUDENT'),
                                                                               ('123456792', 'student123', '赵六', 'MALE', 'zhaoliu@link.cuhk.edu.cn', '13800138004', 'STUDENT');
-- 关键注明：后期对密码实现BCrypt加密后，以上预先添加的用户已弃用
/* 本地通过接口添加的账号：
    管理员：
    "student_id": "Jerry",
    "password": "666666"

    "student_id": "admin_primary1",
    "password": "0000"

    "student_id": "admin_primary2",
    "password": "0000"

   学生：
   MALE:
    "我（作者）": ***, 1234
    "Zhang San": 123456789, 1234
    "Li Si": 123456790, 1234
    "Wang Wu": 123456791, 1234
    "Tom": 123456792, 1234
   FEMALE:
   "Alice": 210001000, 1234
   "Elizabeth": 210001001, 1234
   "Ann": 210001002, 1234
   "Lily": 210001003, 1234
*/



-- 插入宿舍楼数据
INSERT INTO dorm_buildings (name, total_floors, total_rooms, description) VALUES
                                                                              ('逸夫书院D栋', 12, 100, '男生宿舍'),
                                                                              ('逸夫书院C栋', 10, 80, '男生宿舍'),
                                                                              ('逸夫书院E栋', 12, 90, '女生宿舍'),
                                                                              ('厚含书院B栋', 5, 50, '男生宿舍');

-- 插入房间数据
INSERT INTO rooms (building_id, room_number, current_occupants) VALUES
-- 逸夫D
(1, '609',  4),
(1, '401', 0),
(1, '402', 0),
(1, '403', 0),
(1, '1001', 1),
(1, '1002', 2),
(1, '1101', 3),

-- 逸夫C
(2, '301', 0),
(2, '302', 0),
(2, '303', 0),
(2, '304', 0),

-- 逸夫E
(3, '401', 2),
(3, '402', 0),
(3, '403', 0),
(3, '404', 0),

-- 厚含B
(4, '204', 0),
(4, '205', 0),
(4, '206', 0);


-- 插入宿舍分配数据
INSERT INTO dorm_assignments (student_id, room_id, assignment_date) VALUES
                                                                        ('***', 1, '2023-09-01'),
                                                                        ('123456789', 1, '2024-01-01'),
                                                                        ('123456790', 6, '2024-01-01'),
                                                                        ('123456791', 6, '2024-01-01'),
                                                                        ('123456792', 6, '2024-01-01'),

                                                                        ('210001000', 12, '2024-01-01'),
                                                                        ('210001001', 12, '2024-01-01'),
                                                                        ('210001002', 12, '2024-01-01'),
                                                                        ('210001003', 12, '2024-01-01');


-- 插入费用记录
INSERT INTO fee_records (student_id, academic_year, semester, accommodation_fee, electricity_fee, total_amount, due_date, paid, paid_date) VALUES
                                                                                                                                    ('123456789', '2023-2024', 'SPRING', 1500.00, 80.00, 1580.00, '2023-10-01', TRUE, '2023-09-01'),
                                                                                                                                    ('123456790', '2023-2024', 'SPRING', 1500.00, 80.00, 1580.00, '2024-02-01', FALSE,  NULL),
                                                                                                                                    ('123456791', '2023-2024', 'SPRING', 1500.00, 80.00, 1580.00, '2024-02-01', TRUE, '2024-01-20'),
                                                                                                                                    ('123456792', '2023-2024', 'SPRING', 2000.00, 60.00, 2060.00, '2024-02-01', FALSE,  NULL),
                                                                                                                                    ('210001000', '2023-2024', 'SPRING', 1500.00, 80.00, 1580.00, '2024-02-01', FALSE,  NULL),
                                                                                                                                    ('210001001', '2023-2024', 'SPRING', 1500.00, 80.00, 1580.00, '2024-02-01', FALSE,  NULL),
                                                                                                                                    ('210001002', '2023-2024', 'SPRING', 1500.00, 80.00, 1580.00, '2024-02-01', FALSE,  NULL),
                                                                                                                                    ('210001003', '2023-2024', 'SPRING', 1500.00, 80.00, 1580.00, '2024-02-01', FALSE,  NULL);
INSERT INTO fee_records (student_id, academic_year, semester, accommodation_fee, electricity_fee, total_amount, due_date, paid, paid_date) VALUES
                                                                                                                                    ('***', '2023-2024', 'FALL', 1500.00, 80.00, 1580.00, '2024-02-01', FALSE,  NULL);

-- 插入维修申请示例
INSERT INTO maintenance_requests (student_id, room_id, issue_type, description, status, maintenance_notes, estimated_completion) VALUES
                                                                                            ('***', 1, 'PLUMBING', '卫生间水龙头漏水 Leaky faucet', 'COMPLETED', 'The faucet was replaced.', '2023-10-15');

-- 插入调整申请示例
INSERT INTO adjustment_requests (student_id, reason, preferred_building_name, status) VALUES
    ('***', '希望更换到安静的房间', '逸夫书院D栋', 'PENDING');


