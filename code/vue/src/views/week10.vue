<template>
	<div class="app-container">
		<el-descriptions class="margin-top" title="用户信息" :column="2" border>
			<template slot="extra">
				<el-button type="danger" @click="clearToken">退出登录</el-button>
			</template>
			<el-descriptions-item label="ID">{{userInfo.userId}}</el-descriptions-item>
			<el-descriptions-item label="用户名">{{userInfo.userName}}</el-descriptions-item>
			<el-descriptions-item label="电话">{{userInfo.userPhone}}</el-descriptions-item>
			<el-descriptions-item label="密码">{{userInfo.userPwd}}</el-descriptions-item>
			<el-descriptions-item label="身份证">{{userInfo.userCard}}</el-descriptions-item>
			<el-descriptions-item label="状态">{{userInfo.userStatus}}</el-descriptions-item>
			<el-descriptions-item label="类型">{{userInfo.userType}}</el-descriptions-item>
		</el-descriptions>
	</div>
</template>

<script>
	import {
		getUserByUsername
	} from '@/api/user';
	import {
		removeToken
	} from '@/utils/auth';
	export default {
		name: "Week10",
		data() {
			return {
				username: null,
				userInfo: null
			}
		},
		created() {
			this.username = this.$route.query.username;
			this.getUserInfo();
		},
		methods: {
			getUserInfo() {
				getUserByUsername(this.username).then(response => {
					this.userInfo = response.data;
				})
			},
			clearToken() {
				removeToken();
				this.getUserInfo();
			}
		}
	}
</script>

<style>
</style>