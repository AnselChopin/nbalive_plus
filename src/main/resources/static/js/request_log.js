$(document).ready(function () {
    $("#btn_getCounts").click(function () {
        var offset = $("#ipt_offset").val();
        var size = $("#ipt_size").val();
        getMgrCounts(offset,size);
    });

    $("#btn_next").click(function () {
        var offset = parseInt($("#ipt_offset").val());
        var size = parseInt($("#ipt_size").val());
        var o = offset + size;
        $("#ipt_offset").val(parseInt(o));
        getMgrCounts((offset+size),size);
    });

    $("#btn_back").click(function () {
        var offset = $("#ipt_offset").val();
        var size = $("#ipt_size").val();
        var o = offset - size;
        if(o < 0){o = 0;}
        $("#ipt_offset").val(parseInt(o));
        getMgrCounts(o,size);
    });

});

function getMgrCounts(offset,size) {
    loadUrl(API_REQUEST_LOG + "?offset=" + offset + "&size=" + size)
}
