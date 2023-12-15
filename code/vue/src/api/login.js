import require from "../utils/require";

export function login(username, password, verifyCode, uuid, rememberMe) {
    const data = {
        username, password, verifyCode, uuid, rememberMe
    };
    return require({
        url: "/doLogin",
        headers: {
            isToken: false
        },
        method: 'post',
        data: data
    })
}

export function getVerifyImage() {
    return require({
        url: '/vcImage',
        headers: {
            isToken: false
        },
        method: 'get',
        timeout: 20000
    })
}

export function logout() {
    return require({
        url: '/doLogout',
        method: 'get'
    })
}