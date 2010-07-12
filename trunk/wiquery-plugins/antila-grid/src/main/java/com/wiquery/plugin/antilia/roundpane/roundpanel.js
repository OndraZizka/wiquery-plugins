	
	function RoundPanel(id, folded) {
		this.id = id;
		this.folded = folded;
		var body_id= "#"+id+"_body";
		this.body = $(body_id); 
	}
	
	RoundPanel.prototype.toggleFold = function() {
		alert(this.folded);
		if(!this.folded) {			
			this.folded = true;			
			this.body.fadeIn('slow');
		} else {
			this.folded = false;
			this.body.fadeOut('slow');
		}
	}	