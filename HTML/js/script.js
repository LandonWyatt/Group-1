window.onload = function() {

	if (window.addEventListener)
		window.addEventListener('keyup', takeInput, false);

	function takeInput(e) {
		var searchBar = document.getElementById('searchBar');
		var val = searchBar.value;
		console.log(val);
	}
}