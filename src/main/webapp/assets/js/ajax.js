/**
 * jsonp 请求
 * @param url
 * @param data
 * @param callback
 */
function requireDataJsonP(url,data,callback){
    $.ajax({
        url:url.replace(/'/g, '"'),
        dataType:'jsonp',
        type:'GET',
        data : data,
        jsonp : "callback",
        success : function(json) {
            if(json.code == 2){
                redirectAPIAuthPage(window['FDEnv']['os']['apiLoginUrl']);
                return;
            }
            callback(json);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });
}

/**
 * 跳转至登录
 * @param apiLoginUrl
 */
function redirectAPIAuthPage(apiLoginUrl){
    var redirectUrl = window.location.href;
    var email=window['FDEnv']['user']['email'];
    if(email==null || email=='undefined'||  typeof email == "undefined"){
        window.location.href = window.location.protocol + "//" + window.location.host+"/login";
    }else{
        window.location.href = apiLoginUrl + "?user_email="+encodeURIComponent(email)+"&redirectUrl=" + encodeURIComponent(redirectUrl);
    }

}

/**
 * getRequireData
 * @param url
 * @param param
 * @param callback
 */
function requireData(url,data,callback){

    $.ajax({
        url:url,
        dataType:'jsonp',
        type:'GET',
        data : data,
        jsonp : "callback",
        async: false,
        success : function(json) {
            callback(json);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });

}
/**
 * 同步post请求
 * @param url
 * @param data
 * @param callback
 */
function doPost(url, data, callback){
    post(url, data, false, callback)
}

/**
 * 异步post请求
 * @param url
 * @param data
 * @param callback
 */
function doAsyncPost(url, data, callback){
    post(url, data, true, callback)
}


/**
 * 异步get请求
 * @param url
 * @param data
 * @param callback
 */
function doAsyncGet(url, data, callback){
    get(url, data, true, callback)
}

/**
 * get
 * @param url
 * @param data
 * @param isasync
 * @param callback
 */
function get(url, data, isasync, callback) {

    $.ajax({
        url : url,
        type : 'GET',
        data : data,
        async: isasync,
        success : function(json) {
            callback(json);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });
}

/**
 * post
 * @param url
 * @param data
 * @param isasync 是否异步
 * @param callback
 */
function post(url, data, isasync, callback) {
    $.ajax({
        type : 'POST',
        url : url,
        data : data,
        async: isasync,
        success : function(json) {
            callback(json);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });
}

function upload(url,data,callback){
    $.ajax({
        data : data,
        type : "POST",
        url : url,
        cache : false,
        async: false,
        contentType : false,
        processData : false,
        dataType:"json",
        success : function(json) {
            callback(json);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
        }
    });

}


