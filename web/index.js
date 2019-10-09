function getParameterByName(name) {
  let url = window.location.href;
  name = name.replace(/[\[\]]/g, '\\$&');
  let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)');
  let results = regex.exec(url);
  if (!results) return null;
  if (!results[2]) return '';
  return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

var app = new Vue({ 
    el: '#app',
    data: {
        rawName: '',
        m: {},
        monsterReady: false
    },
    methods: {
      loadMonster: function () {
        this.monsterReady = false;
        delete this.m;
        this.askedName = this.rawName;
        var baseUrl = 'http://mounty-fetch.zoumbox.org';
        // var baseUrl = 'http://localhost:8080/mountyFetch';
        var url = baseUrl + '/v1/monsters/fromName?raw=' + this.rawName;
        this.$http.get(url).then(response => {
          console.log(response.body);
          this.m = response.body;
          this.monsterReady = true;
        }, response => {
          // error callback
        });
      },
      goLycos: function () {
        let url = window.location.protocol + '//' + window.location.host + window.location.pathname + '?q=' + encodeURI(this.rawName);
        window.location = url;
      }
    },
    created: function () {
      if (window.location.search) {
        let name = getParameterByName('q');
        if (name) {
          this.rawName = name;
          this.loadMonster();
        }
      }
    },
    mounted: function () {
      document.getElementById("rawNameTF").focus();

      document.getElementById('rawNameTF').onkeydown = function(e){
        if(e.keyCode == 13) {
          this.goLycos();
        }
      }.bind(this);
    }
});
Vue.component('monster-details', {
    props: ['monster', 'askedName'],
    template: `
      <div class="monster-details" align="center">
        <h3>Détails du monstre « {{ this.askedName }} »</h3>
        <table>
          <tr v-if="monster.id">
            <th>Identifiant</th>
            <td colspan="2">{{monster.id}}</td>
          </tr>
          <tr v-if="monster.family">
            <th>Famille</th>
            <td colspan="2">{{monster.family}}</td>
          </tr>
          <tr v-if="monster.baseName">
            <th>Monstre</th>
            <td>{{monster.baseName}}</td>
            <td>{{monster.baseNival}}</td>
          </tr>
          <tr v-if="monster.template">
            <th>Template</th>
            <td>{{monster.template}}</td>
            <td>{{monster.templateBonus < 0 ? '':'+'}}{{monster.templateBonus}}</td>
          </tr>
          <tr v-if="monster.age">
            <th>Âge</th>
            <td>{{monster.age}}</td>
            <td>+{{monster.ageBonus}}</td>
          </tr>
          <tr v-if="monster.fullName">
            <th>Résultat</th>
            <td>{{monster.fullName}}</td>
            <td>{{monster.nival}}</td>
          </tr>
        </table>
      </div>
    `
  });
