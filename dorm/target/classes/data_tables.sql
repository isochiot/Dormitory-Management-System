-- 仅供参考，不使用springboot自动创建，实际人工创建

-- 创建数据库
CREATE DATABASE IF NOT EXISTS dorm_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE dorm_management;

-- 用户表
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     student_id VARCHAR(20) UNIQUE NOT NULL,
                                     password VARCHAR(100) NOT NULL,
                                     name VARCHAR(50) NOT NULL,
                                     gender ENUM('MALE', 'FEMALE') NOT NULL,
                                     email VARCHAR(100),
                                     phone VARCHAR(20),
                                     role ENUM('STUDENT', 'ADMIN') NOT NULL DEFAULT 'STUDENT',
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 宿舍楼表
CREATE TABLE IF NOT EXISTS dorm_buildings (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              name VARCHAR(50) NOT NULL,
                                              total_floors INT NOT NULL,
                                              total_rooms INT NOT NULL,
                                              description TEXT,
                                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 房间表
CREATE TABLE IF NOT EXISTS rooms (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     building_id BIGINT NOT NULL,
                                     room_number VARCHAR(20) NOT NULL,
                                     -- 房间统一为4人间
                                     current_occupants INT DEFAULT 0,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     FOREIGN KEY (building_id) REFERENCES dorm_buildings(id) ON DELETE CASCADE,
                                     UNIQUE KEY unique_building_room (building_id, room_number)
);

-- 宿舍分配表
CREATE TABLE IF NOT EXISTS dorm_assignments (
                                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                student_id VARCHAR(20) NOT NULL,
                                                room_id BIGINT NOT NULL,
                                                assignment_date DATE NOT NULL,
                                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                FOREIGN KEY (student_id) REFERENCES users(student_id) ON DELETE CASCADE,
                                                FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE
);

-- 调整申请表
CREATE TABLE IF NOT EXISTS adjustment_requests (
                                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                   student_id VARCHAR(20) NOT NULL,
                                                   reason TEXT NOT NULL,
                                                   preferred_building_name VARCHAR(50),
                                                   status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
                                                   admin_notes TEXT,
                                                   assigned_room_number VARCHAR(20),
                                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                   FOREIGN KEY (student_id) REFERENCES users(student_id) ON DELETE CASCADE
);

-- 维修申请表
CREATE TABLE IF NOT EXISTS maintenance_requests (
                                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                    student_id VARCHAR(20) NOT NULL,
                                                    room_id BIGINT NOT NULL,
                                                    issue_type ENUM('PLUMBING', 'ELECTRICAL', 'FURNITURE', 'OTHER') NOT NULL,
                                                    description TEXT NOT NULL,
                                                    status ENUM('SUBMITTED', 'IN_PROGRESS', 'COMPLETED') DEFAULT 'SUBMITTED',
                                                    maintenance_notes TEXT,
                                                    estimated_completion DATE,
                                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                    FOREIGN KEY (student_id) REFERENCES users(student_id) ON DELETE CASCADE,
                                                    FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE
);

-- 费用记录表
CREATE TABLE IF NOT EXISTS fee_records (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           student_id VARCHAR(20) NOT NULL,
                                           academic_year VARCHAR(20) NOT NULL,
                                           semester ENUM('SPRING', 'SUMMER', 'FALL') NOT NULL,
                                           accommodation_fee DECIMAL(10,2) NOT NULL DEFAULT 0.00,
                                           electricity_fee DECIMAL(10,2) NOT NULL DEFAULT 0.00,
                                           total_amount DECIMAL(10,2) NOT NULL DEFAULT 0.00,
                                           due_date DATE NOT NULL,
                                           paid BOOLEAN DEFAULT FALSE,
                                           paid_date DATE,
                                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                           FOREIGN KEY (student_id) REFERENCES users(student_id) ON DELETE CASCADE,
                                           UNIQUE KEY unique_student_fee (student_id, academic_year, semester)
);
