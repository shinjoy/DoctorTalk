var chat = {
	socket : null,
	setSocket : function(url) {
		try {
			socket = new WebSocket(url);
			socket.onopen = function(e) {
				chat.onopen(e); 
			};
			socket.close = function(e) {
				chat.onclose(e);
			};
		} catch (e) {
			alert("chat.setSocket error : "+e);
		}
	},
	onopen : function(e) {
		alert("chat.onopen:"+e);
	},
	onclose : function(e) {
		alert("chat.onclose:"+e);
	},
	send : function(message) {
		socket.send(message);
	}
	
};