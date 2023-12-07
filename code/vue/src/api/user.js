import require from "../utils/require";

export function getUserByUsername(username) {
	return require({
	    url: '/10/user/username/' + username,
	    method: 'get'
	})
}

export function getUserById(id) {
	return require({
		url: "/10/user/" + id,
		method: 'get'
	})
}