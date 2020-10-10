
function login_Check(){
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    if(username==""){$.message({
        message:'帐号不能为空！',
        type:'error'
    });return false;}
    if(password==""){$.message({
        message:'密码不能为空！',
        type:'error'
    });return false;}
}

function register_Check(){
    const username = document.getElementById("username-r").value;
    const password1 = document.getElementById("password1").value;
    const password2 = document.getElementById("password2").value;
    const mail = document.getElementById("mailbox").value;

    if(username==""){        	$.message({
        message:'帐号不能为空！',
        type:'error'
    });;return false;}
    if(!(password1 == password2 && password1 != "")){        	$.message({
        message:'密码为空或不一致！',
        type:'error'
    });return false;}
    if(mail==""){        	$.message({
        message:'邮箱不能为空！',
        type:'error'
    });return false;}
    if(mail.indexOf("@") == -1 || mail.indexOf(".") == -1){        	$.message({
        message:'邮箱格式错误！',
        type:'error'
    });return false;}
}

$(function(){
    /** 验证登陆是否成功  */
    $("#form1").ajaxForm(function(data){
        if(data == "false"){
            $.message({
                message:'用户不存在或密码错误！',
                type:'error'
            });
        }else if(data == "true"){
            window.location.href = "/User/FileList";
        }else{
            $.message({
                message:'未知错误！',
                type:'error'
            });
        }
    });
});

$(function(){
    $("#form2").ajaxForm(function(data){
        if(data == "true"){
            $.message({
                message:'注册成功！',
                type:'success'
            });
            // 延迟1.5秒刷新页面
            setTimeout(function (){
                location.reload();
            }, 1500);
        }else if(data == "false"){
            $.message({
                message:'帐号已存在！',
                type:'error'
            });
        }else{
            $.message({
                message:'未知错误！',
                type:'error'
            });
        }
    });
});
