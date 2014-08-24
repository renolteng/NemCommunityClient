define([
	'languages/bulgarian_b',
	'languages/chinese',
	'languages/croatian',
	'languages/english', 
	'languages/french', 
	'languages/german', 
	'languages/italian', 
	'languages/japanese', 
	'languages/special',
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
		italian,
		japanese, 
		special,
		portuguese_br,
		vietnamese, 
		russian, 
		lithuanian) {
            function iterx(obj) {
                for (var property in obj) {
                    if (obj.hasOwnProperty(property)) {
                        if (typeof(obj[property]) == "object") {
                            iterx(obj[property]);
                        } else {
                            if (property !== "logo") {
                                obj[property] = window.atob(obj[property]);
                            }
                        }
                    }
                }
            }
		    iterx(special.texts);
	return [
		bulgarian,
		chinese,
		croatian,
		english,
		french,
		german, 
		italian, 
		japanese, 
		special,
		portuguese_br,
		vietnamese, 
		russian, 
		lithuanian
	];
});
