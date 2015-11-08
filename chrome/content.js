(function (chrome) {
    var js = document.createElement('script');
    js.type = 'text/javascript';
    js.src = chrome.extension.getURL('inject.js');
    document.getElementsByTagName('q')[0].appendChild(js);//q is the name of a search bar in google
}(chrome));