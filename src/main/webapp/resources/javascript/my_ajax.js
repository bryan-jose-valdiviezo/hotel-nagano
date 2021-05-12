/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ajax(url,callback,httpMethod,data) {
    var xhr = new XMLHttpRequest();
    
    if (!data) data = null;
    
    if (!httpMethod) httpMethod = 'GET';
    
    xhr.onreadystatechange = callback;
    
    try 
    {
        xhr.open(httpMethod,url,true);
    }
    catch (e) 
    {
        alert(e);
    }
    
    xhr.send(data);
}
