function AjaxForm(formname) {
	this.formname = formname;
	this.serializeJson = function() {
		var x = this.formname;
		if (typeof (x) != "undefined" && document.forms[x] != null) {
			var form = $("#" + x).serializeArray();
			var result = {};
			$.each(form, function() {
				if (result[this.name] !== undefined) {
					if (!result[this.name].push) {
						result[this.name] = [ result[this.name] ];
					}
					result[this.name].push(this.value || '');
				} else {
					result[this.name] = this.value || '';
				}
			});
			return result;
		}
	};
	this.success = function(data) {
		if(data.error && data.error != "")
			alert(data.error);
		else if(data.op && data.op != "" && data.url && data.url != "") {
			if(data.op == "open")
				window.open(data.url);
			else if(data.op = "redirect")
				window.location.href = data.url;
		}
		if(typeof(success) == "function")
			this.afterSuccess();
	};
	this.afterSuccess = function() {
	};
	this.submit = function(url, success) {
		if(typeof(success) == "function")
			this.afterSuccess = success;
		$.post(url, this.serializeJson(), this.success, "json");
	};
}