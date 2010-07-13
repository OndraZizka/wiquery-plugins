	
	function RoundPanel(id, folded) {
		this.id = id;
		this.folded = folded;
		var body_id= "#"+id+"_body";
		this.body = $(body_id); 
	}
	
	RoundPanel.prototype.toggleFold = function() {
		if(this.folded) {			
			this.folded = false;			
			this.body.fadeIn('slow');
		} else {
			this.folded = true;
			this.body.fadeOut('slow');
		}
	}
	
	RoundPanel.prototype.isFolded = function() {
		return this.folded;
	}