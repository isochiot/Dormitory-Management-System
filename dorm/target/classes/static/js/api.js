// API基础URL
const API_BASE_URL = 'http://localhost:8080';

// 通用API调用函数
async function apiCall(endpoint, options = {}) {
    const url = `${API_BASE_URL}${endpoint}`;

    const defaultOptions = {
        credentials: 'include', // 包含Cookie
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const config = {
        ...defaultOptions,
        ...options,
        headers: {
            ...defaultOptions.headers,
            ...options.headers,
        },
    };

    try {
        const response = await fetch(url, config);
        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.message || '请求失败');
        }

        return data;
    } catch (error) {
        console.error('API调用错误:', error);
        throw error;
    }
}

// 学生端API
const studentAPI = {
    // 登录
    login: (studentId, password) =>
        apiCall('/student/login', {
            method: 'POST',
            body: JSON.stringify({ student_id: studentId, password })
        }),

    // 注册
    register: (userData) =>
        apiCall('/student/register', {
            method: 'POST',
            body: JSON.stringify(userData)
        }),

    // 获取当前用户信息
    getCurrentUser: () =>
        apiCall('/student/current'),

    // 修改个人信息
    updateProfile: (profileData) =>
        apiCall('/student/profile', {
            method: 'PUT',
            body: JSON.stringify(profileData)
        }),

    // 获取宿舍信息
    getDormInfo: () =>
        apiCall('/student/dorm_info'),

    // 获取楼栋列表
    getBuildings: () =>
        apiCall('/student/buildings'),

    // 提交调整申请
    submitAdjustmentRequest: (requestData) =>
        apiCall('/student/adjustment_request', {
            method: 'POST',
            body: JSON.stringify(requestData)
        }),

    // 获取我的申请
    getMyRequests: () =>
        apiCall('/student/my_requests'),

    // 获取费用信息
    getFees: () =>
        apiCall('/student/fees'),

    // 提交维修请求
    submitMaintenance: (requestData) =>
        apiCall('/student/maintenance', {
            method: 'POST',
            body: JSON.stringify(requestData)
        }),

    // 获取维修请求
    getMaintenance: () =>
        apiCall('/student/maintenance'),

    // 退出登录
    logout: () =>
        apiCall('/logout', {
            method: 'POST'
        })
};

// 工具函数
const utils = {
    // 显示消息
    showMessage: (message, type = 'success') => {
        const messageEl = document.createElement('div');
        messageEl.className = `message ${type}`;
        messageEl.textContent = message;

        document.body.prepend(messageEl);

        setTimeout(() => {
            messageEl.remove();
        }, 3000);
    },

    // 格式化日期
    formatDate: (dateString) => {
        if (!dateString) return '-';
        const date = new Date(dateString);
        return date.toLocaleString('zh-CN');
    },

    // 检查登录状态
    checkLogin: async () => {
        try {
            const user = await studentAPI.getCurrentUser();
            return user.data;
        } catch (error) {
            return null;
        }
    },

    // 跳转页面
    redirectTo: (url) => {
        window.location.href = url;
    },

    // 双语文本处理
    bilingualText: (chinese, english) => {
        return `${chinese}<br>${english}`;
    }
};

// 管理员端API
const adminAPI = {
    // 登录
    login: (studentId, password) =>
        apiCall('/admin/login', {
            method: 'POST',
            body: JSON.stringify({ student_id: studentId, password })
        }),

    // 获取当前管理员信息
    getCurrentUser: () =>
        apiCall('/admin/current'),

    // 修改个人信息
    updateProfile: (profileData) =>
        apiCall('/admin/profile', {
            method: 'PUT',
            body: JSON.stringify(profileData)
        }),

    // 获取调整申请列表
    getAdjustmentRequests: () =>
        apiCall('/admin/adjustment_requests'),

    // 处理调整申请
    handleAdjustment: (requestId, handleData) =>
        apiCall(`/admin/handle_adjustment/${requestId}`, {
            method: 'POST',
            body: JSON.stringify(handleData)
        }),

    // 获取楼栋列表（带ID）
    getBuildings: () =>
        apiCall('/admin/buildings'),

    // 获取宿舍状态
    getDormStatus: (buildingId = '') => {
        const url = buildingId ? `/admin/dorm_status?building_id=${buildingId}` : '/admin/dorm_status';
        return apiCall(url);
    },

    // 获取缴费状态
    getFeeStatus: () =>
        apiCall('/admin/fee_status'),

    // 获取维修请求列表
    getMaintenanceRequests: (status = '') => {
        const url = status ? `/admin/maintenance_requests?status=${status}` : '/admin/maintenance_requests';
        return apiCall(url);
    },

    // 更新维修状态
    updateMaintenance: (requestId, updateData) =>
        apiCall(`/admin/maintenance_request/${requestId}`, {
            method: 'PUT',
            body: JSON.stringify(updateData)
        }),

    // 退出登录
    logout: () =>
        apiCall('/logout', {
            method: 'POST'
        })
};