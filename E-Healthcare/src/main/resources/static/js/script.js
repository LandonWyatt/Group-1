/* Search Script */
function takeInput() {
	var searchBarVal = document.getElementById("searchBar").value;
	
	let data = {searchStr: searchBarVal};
	
	fetch("/productChangeSearch", {
	  method: "POST",
	  headers: {'Content-Type': 'application/json'}, 
	  body: JSON.stringify(data)
	})
}

/* Number of Entries Script */
function showEntries() {
	var numEntriesVal = document.getElementById("numEntries").value;
	
	let data = {numEntries: numEntriesVal};

	fetch("/productChangeEntries", {
	  method: "POST",
	  headers: {'Content-Type': 'application/json'}, 
	  body: JSON.stringify(data)
	})
}

/* Activation Script */
function activate(sel) {
	fetch("/productActive/" + sel.value, {
	  method: "PUT",
	  headers: {'Content-Type': 'application/json'}
	})
}

/* Sorting Script */
function sort(sortOrder) {
	fetch("/productSearch/" + sortOrder, {
	  method: "POST",
	  headers: {'Content-Type': 'application/json'}
	})
}





