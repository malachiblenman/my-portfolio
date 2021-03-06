// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
/* All of this is unused, by the way.*/
/**
 * Adds a random fact to the page.
 */
function addRandomFact() {
  const greetings =
      ['I am president of my space club!', 'Two projects I am working on right now include a Mars Rover & Rocket', 'I plan on building an observatory for my school', 'I have interned at NASA before!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;

}

function FetchDispGreeting (){
      fetch("/data").then(response => response.json()).then((messages) => {
            const statsListElement = document.getElementById('CommentContainer');
            var i = 0;
            console.log(messages[i])
             statsListElement.innerHTML = '';
            while (messages[i] != null){
                 statsListElement.appendChild(
                 createListElement('Comment: ' + messages[i]));
            i++;
         }
     });
}

function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}    