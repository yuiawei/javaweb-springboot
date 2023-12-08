import require from "../utils/require";

export function getUserByUsername(username) {
	return require({
	    url: '/sys/user/username/' + username,
	    method: 'get'
	})
}

export function getUserById(id) {
	return require({
		url: "/sys/user/" + id,
		method: 'get'
	})
}