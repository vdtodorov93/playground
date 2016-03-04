function loadImage(url) {
	var promise = new Promise(
		function resolver(resolve, reject) {
			var img = new Image();
			img.src = url;
			img.onload = function () {
				resolve(img);
			};

			img.onerror = function (e) {
				reject(e);
			};
		}
	);
	return promise;
}

loadImage('security_holes.png').then(function (img) {
	document.body.appendChild(img);
}).catch(function (e) {
	console.log('Error occurred while loading image');
	console.log(e);
});




//STATES
var promise = new Promise(function (resolve, reject) {
	resolve(Math.PI);
	reject(0);// Does nothing
	resolve(Math.sqrt(-1)); // Does nothing
});

//USING ONE PROMICE MULTIPLE TIMES
var user = {
	profilePromise: null,
	getProfile: function () {
		if (!this.profilePromise) {
		// Assume ajax() returns a promise that is eventually
		// fulfilled with {name: 'Samantha', subscribedToSpam: true}
		this.profilePromise = ajax(/*someurl*/);
	}

	return this.profilePromise;
	}
};

var navbar = {
show: function (user) {
	user.getProfile().then(function (profile) {
		console.log('*** Navbar ***');
		console.log('Name: ' + profile.name);
	});
	}
};

var account = {
	show: function (user) {
		user.getProfile().then(function (profile) {

			console.log('*** Account Info ***');
			console.log('Name: ' + profile.name);
			console.log('Send lots of email? ' + profile.subscribedToSpam);
		});
	}
};

navbar.show(user);
account.show(user);
//END 