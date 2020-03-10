var vue = new Vue({
    el: "#login-form",
    data: {
        user: {
            email:"",
            password:""
        },
        fail_login:""
    },
    methods: {
        verify: function (event) {
            event.preventDefault();
            var _this = this;
            var jsonuser=JSON.stringify(this.user);

            axios.post("http://localhost:8080/login",jsonuser, {headers: {'Content-Type': 'application/json'}}).then(function (response) {
                let statecode = response.data.state;
                if (statecode === 1){
                    window.location.href="/UserInformation.html";
                }else if(statecode === -1004){
                    _this.fail_login="密码错误";
                }else{
                    console.log(response);
                    _this.fail_login="邮箱未注册";
                }
            }).catch(function (err) {
                console.log(err);
            });

            // let config = {
            //     headers: {
            //         'content-type': 'application/json'
            //     }
            // };
            //
            // this.$http.post("http://localhost:8080/login",jsonuser, config).then(function (response) {
            //     console.log(response)
            // }).catch((error) => {
            //     console.log(error)
            // })
        }

    }
    // created:function(){
    //     this.findById();
    // }
});