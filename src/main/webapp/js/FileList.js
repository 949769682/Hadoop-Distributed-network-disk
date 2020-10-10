
function getList(){
    $("#form").ajaxForm(function(data){
        let str = "";
        for(let i=0;i<data.length;i++){
            str += "<tr><td class='filename'>" + data[i].filename + "</td><td>" +
                data[i].filesize + "</td><td>" +
                data[i].filetime + "</td>" +
                "<td><input type='button' class='button' onclick='Delete(this)' class='delete' value='删除'/></td>" +
                "<td><input type='button' class='button' onclick='Download(this)' class='download' value='下载'/></td>" +
                "<td><input type='button' class='button' onclick='Play(this)' class='play' value='播放'/></td>" +
                "</tr>";
        }
        clear();
        $("#tab").append(str);
        $.message({
            message:'上传成功！',
            type:'success'
        });

    });
}
function file_onclick() {
    return $("#file").click();
}
function file_name(obj) {
    $("span").html(obj.value);
}
function clear() {
    $("#tab  tr:not(:first)").html("");
}
function Upload() {
    if($("#file").val()!=null && $("#file").val()!=""){
        $.message({
            message:'正在上传！',
            type:'info'
        });
    }else{
        $.message({
            message:'请选择要上传的文件！',
            type:'warning'
        });
        return false;
    }
}
function Download(button) {
    let filename = $(button).parents("tr").find(".filename").text();
    window.location.href = "/User/download?filename="+filename;
    $.message("开始下载");
}
function Play(button) {
    let filename = $(button).parents("tr").find(".filename").text();
    window.location.href = "/pages/play.jsp?filename="+filename;
}
function Delete(button) {
    let filename = $(button).parents("tr").find(".filename").text();
    let data = "filename=" + filename;
    $.get("/User/delete",data,function (msg) {
        if(msg == true){
            $.message("删除成功");
            $(button).parents("tr").remove();
        }
        if(msg == false){
            $.message({
                message:'删除失败',
                type:'error'
            });
        }
    });

}
$(function (){
    getList();

});
