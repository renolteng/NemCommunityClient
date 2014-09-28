define([
	'languages/bulgarian_b',
	'languages/chinese',
	'languages/croatian',
	'languages/english', 
	'languages/french', 
	'languages/german', 
	'languages/hindi_b', 
	'languages/italian', 
	'languages/japanese', 
	'languages/spanish',
	'languages/pirate',
	'languages/portuguese_br',
	'languages/vietnamese', 
	'languages/russian', 
	'languages/lithuanian'], function(
		bulgarian,
		chinese,
		croatian,
		english,
		french,
		german, 
		hindi,
		italian,
		japanese, 
		spanish,
		pirate,
		portuguese_br,
		vietnamese, 
		russian, 
		lithuanian) {
            function iterx(obj) {
                for (var property in obj) {
                    if (obj.hasOwnProperty(property)) {
                        if (typeof(obj[property]) == "object") {
                        	if (property !== 'preferences') {
                            	iterx(obj[property]);
                            }
                        } else {
                            if (property !== 'logo') {
                                obj[property] = window.atob(obj[property]);
                            }
                        }
                    }
                }
            }
		    iterx(pirate.texts);
	return [
		bulgarian,
		chinese,
		croatian,
		english,
		french,
		german, 
		hindi,
		italian, 
		japanese,
		spanish,
		pirate,
		portuguese_br,
		vietnamese, 
		russian, 
		lithuanian
	];
});
