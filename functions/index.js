const functions = require('firebase-functions');
const request = require ('request-promise')

exports.indexPostsToElastic =  functions.database.ref('/posts/{post_id}')
	.onWrite(event =>{
		let postData = event.data.val();
		let post_id = event.params.post_id;
		
		console.log('Indexing the posst:',postData);
		
		let elasticSearchConfig = functions.config().elasticSearch;
		let elasticSearchUrl = 'http://35.224.115.175//elasticsearch/' + 'posts/post/' + post_id;
		let elasticSearchMethod = postData ? 'POST' : 'DELETE';
		
		let elasticSearchRequest = {
			method: elasticSearchMethod,
				url: elasticSearchUrl,
				auth:{
					username: 'user',
					password: 'LTddciU7GeX5',
				},
				body: postData,
				json: true
			};
			
		return request(elasticSearchRequest).then(response =>{
			return console.log("Elasticsearch response", response);
		});
	});
