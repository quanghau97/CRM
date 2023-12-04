$(document).ready(function() {
	// logic code


	$(".btn-xoa").click(function() {
		var id = $(this).attr("id-task")
		var This = $(this)
		// GỌI VÀO ĐƯỜNG DẪN VÀ LẤY DỮ LIỆU ĐÓ TRẢ RA
		$.ajax({
			method: "DELETE",
			url: "http://localhost:8080/helloservlet/api/task?id=" + id,
			//	data: { name: "John", location: "Boston" }  // CHỈ DÀNH CHO PHƯƠNG THỨC POST
		})
			.done(function(result) {
				if (result.data == true) {
					This.closest("tr").remove()
				}
				console.log(result)
			})


	})
	
	
})

