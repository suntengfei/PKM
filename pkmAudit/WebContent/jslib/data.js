//这里的数据对页面中回填的数据进行数据字典支撑 Longleg
function Dt(key, val){
	this.key = key;
	this.val = val;
}
var allData={
		"check" : [
			 	new Dt("未审核","0"),
				new Dt("审核通过","1"),
			 	new Dt("审核未通过","2")
		]
};


function getKeyByVal(val, type){
	var ar = allData[type];
	if(ar==null){
		return null;
	}
	for(var i = 0; i < ar.length; i++){
		if(ar[i].val == val){
			return ar[i].key;
		}
	}
}
function getValByKey(key, type){
	var ar = allData[type];
	if(ar==null){
		return null;
	}
	for(var i = 0; i < ar.length; i++){
		if(ar[i].key == key){
			return ar[i].val;
		}
	}
}

