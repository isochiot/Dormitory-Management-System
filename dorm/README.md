# CUHKSZ Dormitory Management System

## Project Overview

The CUHKSZ Dormitory Management System is a modern dormitory management platform built with Spring Boot + MySQL + Frontend static pages, providing convenient online services for students and dormitory administrators at The Chinese University of Hong Kong, Shenzhen.

## System Features

### Student Features
- **Account Management**: Student registration, login, and profile updates
- **Dormitory Information**: View personal dormitory assignment and roommate details
- **Application Services**: Submit dormitory adjustment requests and track application status
- **Fee Inquiry**: Check dormitory fees and payment status
- **Maintenance Services**: Submit maintenance requests and track repair progress

### Administrator Features
- **Account Management**: Administrator login and profile management
- **Application Processing**: Approve or reject student dormitory adjustment requests
- **Status Monitoring**: View dormitory occupancy and room availability
- **Maintenance Management**: Handle student maintenance requests and update status
- **Fee Management**: Monitor student payment status

## Technology Stack

### Backend Technologies
- **Framework**: Spring Boot 3.5.7
- **Security**: Spring Security + BCrypt password encryption
- **Data Persistence**: Spring Data JPA + Hibernate
- **Database**: MySQL 8.0+
- **Session Management**: Jakarta Servlet Session

### Frontend Technologies
- **Core Technologies**: HTML5 + CSS3 + JavaScript (ES6+)
- **Styling Framework**: Native CSS with responsive design
- **API Communication**: Fetch API + JSON
- **Session Management**: Cookie-based Session authentication

## Quick Start

### Prerequisites
- Java 17+
- MySQL 8.0+
- Maven 3.6+

### Database Initialization

1. Create MySQL database:
```sql
CREATE DATABASE dorm_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Execute the SQL script in the project to initialize database tables and test data.

### Project Configuration

1. Modify database connection settings in `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/dorm_management
    username: your_username
    password: your_password
```

2. Configure session timeout (optional):
```yaml
server:
  servlet:
    session:
      timeout: 30m
```

### Running the Project

1. Compile the project using Maven:
```bash
mvn clean compile
```

2. Run the Spring Boot application:
```bash
mvn spring-boot:run
```

3. Access the system:
   - Homepage: http://localhost:8080
   - Student Login: http://localhost:8080/student_login.html
   - Admin Login: http://localhost:8080/admin_login.html

## Project Structure

```
src/main/java/com/cuhksz/dorm/
├── config/          
├── controller/      
├── service/        
├── mapper/          
├── pojo/          
├── util/            
└── interceptor/     

src/main/resources/
├── static/         
│   ├── css/
│   ├── js/
│   └── *.html
└── application.yml  
```

## API Specification

### Unified Response Format
```json
{
  "code": 1,        // 0-Failure, 1-Success
  "message": "Operation result description",
  "data": {}        // Response data
}
```

### Main API Endpoints

#### Student Endpoints
- `POST /student/login` - Student login
- `POST /student/register` - Student registration
- `GET /student/dorm_info` - Get dormitory information
- `POST /student/adjustment_request` - Submit adjustment request
- `GET /student/fees` - Query fee information
- `POST /student/maintenance` - Submit maintenance request

#### Administrator Endpoints
- `POST /admin/login` - Administrator login
- `GET /admin/adjustment_requests` - Get adjustment request list
- `POST /admin/handle_adjustment/{id}` - Process adjustment request
- `GET /admin/dorm_status` - View dormitory status
- `GET /admin/fee_status` - View payment status
- `PUT /admin/maintenance_request/{id}` - Update maintenance status

## Security Features

- **Password Security**: Passwords encrypted using BCrypt algorithm
- **Session Management**: Session-based user authentication and authorization
- **Access Control**: Role-based access control separating student and administrator permissions
- **Input Validation**: Frontend and backend data format validation

## Deployment Guide

### Development Environment
1. Ensure MySQL service is running
2. Import database initialization script
3. Modify database connection settings in configuration file
4. Run Spring Boot application
5. Access application via browser

### Production Environment Recommendations
1. Use HTTPS for encrypted transmission
2. Configure database connection pool
3. Set appropriate session timeout
4. Configure logging and monitoring
5. Use reverse proxy (e.g., Nginx)

## Important Notes

1. **Session Configuration**: The system uses Session for user state management, ensure browser cookies are enabled
2. **Password Security**: Change default account passwords on first use
3. **Data Backup**: Regularly backup database data
4. **Browser Compatibility**: Recommended to use modern browsers (Chrome, Firefox, Edge, etc.)

## Development Team

- Project Developer: Zhou Yifan


-----
# 中文版：

# CUHKSZ宿舍管理系统

## 项目简介

CUHKSZ宿舍管理系统是一个基于Spring Boot + MySQL + 前端静态页面的现代化宿舍管理系统，为香港中文大学（深圳）的学生和宿舍管理人员提供便捷的在线服务。

## 系统功能

### 学生端功能
- **账户管理**：学生注册、登录、个人信息修改
- **宿舍信息**：查看个人宿舍分配详情及室友信息
- **申请服务**：提交宿舍调整申请，查看申请状态
- **费用查询**：查看宿舍费用详情及缴费状态
- **维修服务**：提交宿舍维修请求，跟踪维修进度

### 管理员端功能
- **账户管理**：管理员登录、个人信息管理
- **申请处理**：审批或驳回学生宿舍调整申请
- **状态监控**：查看宿舍入住情况、房间可用性
- **维修管理**：处理学生维修请求并更新状态
- **费用管理**：查看学生缴费状态

## 技术栈

### 后端技术
- **框架**：Spring Boot 3.5.7
- **安全**：Spring Security + BCrypt密码加密
- **数据持久化**：Spring Data JPA + Hibernate
- **数据库**：MySQL 8.0+
- **Session管理**：Jakarta Servlet Session

### 前端技术
- **基础技术**：HTML5 + CSS3 + JavaScript (ES6+)
- **样式框架**：原生CSS，响应式设计
- **API交互**：Fetch API + JSON
- **会话管理**：基于Cookie的Session认证

## 快速开始

### 环境要求
- Java 17+
- MySQL 8.0+
- Maven 3.6+

### 数据库初始化

1. 创建MySQL数据库：
```sql
CREATE DATABASE dorm_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 执行项目中的SQL脚本初始化数据表结构和测试数据。

### 项目配置

1. 修改 `application.yml` 中的数据库连接配置：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/dorm_management
    username: your_username
    password: your_password
```

2. 配置Session超时时间（可选）：
```yaml
server:
  servlet:
    session:
      timeout: 30m
```

### 运行项目

1. 使用Maven编译项目：
```bash
mvn clean compile
```

2. 运行Spring Boot应用：
```bash
mvn spring-boot:run
```

3. 访问系统：
    - 首页：http://localhost:8080
    - 学生登录：http://localhost:8080/student_login.html
    - 管理员登录：http://localhost:8080/admin_login.html


## 项目结构

```
src/main/java/com/cuhksz/dorm/
├── config/          # 配置类（安全、Session等）
├── controller/      # 控制器层
├── service/         # 服务层接口和实现
├── mapper/          # 访问数据库层
├── pojo/          # 实体类
├── util/            # 工具类
└── interceptor/     # 拦截器

src/main/resources/
├── static/          # 前端静态资源
│   ├── css/
│   ├── js/
│   └── *.html
└── application.yml  # 应用配置文件
```

## API接口规范

### 统一响应格式
```json
{
  "code": 1,        // 0-失败，1-成功
  "message": "操作结果描述",
  "data": {}        // 响应数据
}
```

### 主要接口端点

#### 学生端接口
- `POST /student/login` - 学生登录
- `POST /student/register` - 学生注册
- `GET /student/dorm_info` - 获取宿舍信息
- `POST /student/adjustment_request` - 提交调整申请
- `GET /student/fees` - 查询费用信息
- `POST /student/maintenance` - 提交维修请求

#### 管理员端接口
- `POST /admin/login` - 管理员登录
- `GET /admin/adjustment_requests` - 获取调整申请列表
- `POST /admin/handle_adjustment/{id}` - 处理调整申请
- `GET /admin/dorm_status` - 查看宿舍状态
- `GET /admin/fee_status` - 查看缴费状态
- `PUT /admin/maintenance_request/{id}` - 更新维修状态

## 安全特性

- **密码安全**：使用BCrypt算法加密存储密码
- **会话管理**：基于Session的用户认证和授权
- **权限控制**：学生和管理员角色分离，接口访问权限控制
- **输入验证**：前后端数据格式验证

## 部署说明

### 开发环境
1. 确保MySQL服务运行
2. 导入数据库初始化脚本
3. 修改配置文件中的数据库连接信息
4. 运行Spring Boot应用
5. 通过浏览器访问应用

### 生产环境建议
1. 使用HTTPS加密传输
2. 配置数据库连接池
3. 设置适当的Session超时时间
4. 配置日志记录和监控
5. 使用反向代理（如Nginx）

## 注意事项

1. **Session配置**：系统使用Session进行用户状态管理，请确保浏览器启用Cookie
2. **密码安全**：首次使用建议修改默认账户密码
3. **数据备份**：定期备份数据库数据
4. **浏览器兼容**：建议使用现代浏览器（Chrome、Firefox、Edge等）

## 开发团队

- 项目开发者：Zhou Yifan
- 开发时间：2025年
