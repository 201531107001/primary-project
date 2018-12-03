new Vue({
	el: '#insertForm',
	data: {
		username: '',
		password: '',
		sex: '1',
		age: null
	},
	methods: {
		submitForm: function() {
			axios.post('../student/add', {
				username: this.username,
				password: this.password,
				sex:this.sex,
				age:this.age
			})
			.then(function(response) {
				console.log(response);
			})
			.catch(function(error) {
				console.log(error);
			});
		}
	}
})
