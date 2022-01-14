/* Search Script */
function takeInput() {
	var search = document.getElementById("searchBar");
	console.log(search.value);
}

/* Number of Entries Script */
function showEntries() {
	var numChosen = document.getElementById("numEntries").value;
	
	let data = {numChosen: numChosen};

	fetch("/productChange", {
	  method: "POST",
	  headers: {'Content-Type': 'application/json'}, 
	  body: JSON.stringify(data)
	}).then(res => {
	  console.log("Request complete! response:", res);
	});
	
	/*
	Look at th:fragment 
	https://www.baeldung.com/spring-thymeleaf-fragments
	https://frontbackend.com/thymeleaf/how-to-work-with-fragments-in-thymeleaf
	*/
	
	/*setTimeout(() => {
		location.reload();
	}, 500)

	console.log(numChosen2);
	document.querySelector('#numEntries option[value="' + numChosen2 +'"]').selected = true;*/

}