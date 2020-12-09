var manager = {

	init : function (){
		var _this = this
	},

	reg : function (){

		var data = {
			managerNm : $('#managerNm').val(),
			managerTelNo : $('#managerTelNo').val(),
		}
		//
		// if(data.managerNm == ""){
		// 	alert("담당자 이름을 입력해주세요");
		// 	return false;
		// }
		//
		// if(data.managerTelNo == ""){
		// 	alert("담당자 연락처를 입력해주세요");
		// 	return false;
		// }

		$.ajax({
			url : "/manager/reg",
			dataType : 'json',
			type : 'post',
			contentType:'application/json; charset=utf8',
			data : JSON.stringify(data)
		}).done(function (result){
			console.log(result)
			alert('프로젝트 등록이 완료되었습니다.');
			window.location.href = '/manager/list';
		}).fail(function (error){
			alert(JSON.stringify(error));
		});


	}
}