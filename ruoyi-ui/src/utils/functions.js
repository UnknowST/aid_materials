exports.install = function (Vue, options) {
    Vue.prototype.getaddress = function (address) {
       
        var reg = /.+?(省|市|自治区|自治州|县|区|旗)/g; // 省市区的正则
        //var reg = "(?<province>[^省]+省|[^自治区]+自治区|.+市)(?<city>[^自治州]+自治州|.+区划|[^市]+市|.+区)?(?<county>[^市]+市|[^县]+县|[^旗]+旗|.+区)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        str=address.match(reg)
        var len=0;
        for(var i=0;i<str.length;i++){
            len+=str[i].length;
        }
        str.push(address.slice(len-address.length))
        return str;
        
    };
    
};