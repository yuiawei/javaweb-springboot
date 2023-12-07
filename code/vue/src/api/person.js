import require from "../utils/require";

export function getPersonInfo(username) {
    return require({
        url: "/8/person-center",
        method: "get",
        params: {username}
    })
}