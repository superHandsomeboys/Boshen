var vue = new Vue({
    el:"#user_information",
    data:{
        user:{
            userName:"",
            password:"",
            mail:"",
            phone:"",
            introduce:""
        },
        userType:"",
        user_avatar:{
            userId:"",
            avatar:"/asserts/img/1.jpg"
        }
    },
    methods:{
        get_information:function () {
            var _this = this;
            axios.get("/user").then(function (response) {
                let resdata = response.data;
                _this.user_avatar.userId = resdata.data.userId;
                _this.user.userName = resdata.data.userName;
                _this.user.password = resdata.data.password;
                _this.user.mail = resdata.data.mail;
                _this.user.phone = resdata.data.phone;
                _this.user.introduce = resdata.data.introduce;
                if(resdata.data.userType===1){
                    _this.userType="普通员工";
                }else if(resdata.data.userType===2){
                    _this.userType="公司员工";
                }else{
                    _this.userType="超级管理员";
                }
                _this.user_avatar.avatar = resdata.data.avatar;
            }).catch(function (error) {
                console.log(error);
            });
        },
        update_information:function () {
            var _this = this;
            var jsonuser = JSON.stringify(this.user_avatar);
            axios.put("/user",jsonuser).then(function (response) {
                if(response.state!==1){
                    alert("修改失败！");
                }else {
                    _this.get_information();
                }
            }).catch(function (reason) {
                console.log(reason);
            });
        },
        update_avatar:function () {
            var jsonava=JSON.stringify(this.userId,this.avatar);
            axios.put("/user/avatar",jsonava).then(function (response) {
                if(response.state!==1){
                    alert("修改头像失败，请选择jpg和png格式图片。");
                }
            }).catch(function (reason) {
                console.log(reason);
            });
        }
    },
    created:function () {
        this.get_information();
    }
});