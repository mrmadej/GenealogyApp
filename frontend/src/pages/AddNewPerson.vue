<template>
  <div class="q-pa-md" style="max-width: 400px">

    <q-form
      @submit="sendDataToDataBase"
      @reset="onReset"
      class="q-gutter-md"
    >
      <q-input
        filled
        v-model="name"
        label="Name"
        lazy-rules
        :rules="[ val => val && val.length > 0 || 'Please type something']"
      />

      <q-input
        filled
        v-model="surname"
        label="Surname"
        lazy-rules
        :rules="[ val => val && val.length > 0 || 'Please type something']"
      />
      <div>
        <input v-model="searchFather" @input="searchItems(this.fatherFlagConst)" placeholder="Insert name and surname" />
        <select v-model="fatherId" @change="handleChangeInSelect(this.fatherFlagConst)">
          <option v-for="item in filteredFathers" :key="item.id" :value="item.id">
            {{ item.name }} {{item.surname}} ({{item.birth}} - {{item.death}})
          </option>
        </select>
      </div>
      <div>
        <input v-model="searchMother" @input="searchItems(this.motherFlagConst)" placeholder="Insert name and surname" />
        <select v-model="motherId" @change="handleChangeInSelect(this.motherFlagConst)">
          <option v-for="item in filteredMothers" :key="item.id" :value="item.id">
            {{ item.name }} {{item.surname}} ({{item.birth}} - {{item.death}})
          </option>
        </select>
      </div>
      <div class="switch-container">
        <label class="switch">
          <input type="checkbox" id="birthCheckbox" @click="birthFlagChanger">
          <span class="slider round"></span>
        </label>
        <span class="label">Birth date</span>
      </div>

      <div class="switch-container">
        <label class="switch">
          <input type="checkbox" id="deathCheckbox" @click="deathFlagChanger">
          <span class="slider round"></span>
        </label>
        <span class="label">Death date</span>
      </div>

      <div v-if="birthFlag">
        <label class="date">Birth Date</label>
        <q-date
          filled
          v-model="birth"
          label="Date of Birth"
          mask="YYYY-MM-DD"
        />
      </div>
      <div v-if="deathFlag">
        <label class="date">Death Date</label>
        <q-date
          filled
          v-model="death"
          label="Date of Death"
          mask="YYYY-MM-DD"
        />
      </div>



      <div>
        <q-btn label="Submit" type="submit" color="primary"/>
        <q-btn label="Reset" type="reset" color="primary" flat class="q-ml-sm" />
      </div>
    </q-form>


  </div>
</template>

<script>
import axios from "axios";
import {isBooleanLike} from "eslint-plugin-vue/lib/utils/ts-utils/typescript";

export default {
  props: {
    person: {
      type: Object,
      default: () => ({}),
      id: Number,
      name: String,
      surname: String,
      birth: Date,
      death: Date,
      father: Object,
      mother: Object
    },
  },
  mounted() {
    console.log('W add new person component: ');
    //console.log(this.person.id);
  },

  // inject: {
  //   person: {
  //     type: Object,
  //     default: () => ({}),
  //     id: Number,
  //     name: String,
  //     surname: String,
  //     birth: Date,
  //     death: Date,
  //     father: Object,
  //     mother: Object
  //   },
  //
  // },
  data() {
    const personData = this.person || {};
    const father = personData.father || {};
    const mother = personData.mother || {};

    return {
      personId: personData.id || -1,
      fatherFlagConst: 0,
      motherFlagConst: 1,
      name: personData.name || '',
      surname: personData.surname || '',
      birth: null,
      death: null,
      birthFlag: false,
      deathFlag: false,
      fatherId: father.id || null,
      motherId: mother.id || null,
      searchFather: `${father.name || ''} ${father.surname || ''}`,
      searchMother: `${mother.name || ''} ${mother.surname || ''}`,
      filteredFathers: [],
      filteredMothers: [],
    };
  },
  methods: {
    sendDataToDataBase() {
      const formData = {
        id: this.personId,
        name: this.name,
        surname: this.surname,
        birth: this.birth,
        death: this.death,
        motherId: this.motherId,
        fatherId: this.fatherId
      };
      console.log('Wysyłanie danych: idFather: ' + this.fatherId)
      axios.post('/Main/savePerson', formData)
        .then(response => {
          console.log('Response from server: ', response.data);
        })
        .catch(error => {
          console.error('Error: ', error);
        });
      console.log('submited');
      console.log('ID: ' + this.personId);
      this.onReset();
      // this.personsListFlag = true;
    },
    onReset() {
      this.name = '';
      this.surname = '';
      this.birth = null;
      this.death = null;
      this.birthFlag = false;
      this.deathFlag = false;
      this.searchFather = '';
      this.searchMother = '';
      this.fatherId = null;
      this.motherId = null;
      this.filteredFathers = [];
      this.filteredMothers = [];
      document.getElementById('birthCheckbox').checked = false;
      document.getElementById('deathCheckbox').checked = false;
    },
    birthFlagChanger() {
      this.birth = null;
      this.birthFlag = !this.birthFlag;
    },
    deathFlagChanger() {
      this.death = null;
      this.deathFlag = !this.deathFlag;
    },
    searchItems(flag) {
      console.log( "Szukam cos ",this.person)
      let searchPerson;
      if(flag === this.fatherFlagConst)
      {
        searchPerson = this.searchFather;
      }
      else
      {
        searchPerson = this.searchMother;
      }

      if(searchPerson.length >= 3)
      {
        const searchedText = searchPerson.split(' ', 2);
        const searchedName = searchedText.at(0);
        let searchedSurname = null;
        if(searchedText.length < 2)
        {
          searchedSurname = '';
        }
        else
        {
          searchedSurname = searchedText.at(1);
        }

        console.log(searchedSurname);
        axios.get("http://localhost:8081/Main/findPersonByNameAndSurnameLike",
          {
            params: {
              name: searchedName,
              surname: searchedSurname
            }
          })
          .then(response => {
            console.log("otryzmane dane", response.data);
            if(flag === this.fatherFlagConst)
            {
              this.filteredFathers = response.data;
            }
            else
            {
              this.filteredMothers = response.data;
            }
            })
          .catch(error => {console.log("Błąd pobierania danych", error)});
      }
      else
      {

        if(flag === this.fatherFlagConst)
        {
          this.filteredFathers = [];
          this.fatherId = null;
        }
        else
        {
          this.filteredMothers = [];
          this.motherId = null;
        }
      }
    },
    handleChangeInSelect(flag)
    {
      if(flag === this.fatherFlagConst)
      {
        const item = this.filteredFathers.find(item => item.id === this.fatherId);
        this.searchFather = item.name + ' ' + item.surname;
      }
      else
      {
        const item = this.filteredMothers.find(item => item.id === this.motherId);
        this.searchMother = item.name + ' ' + item.surname;
      }

      console.log(this.fatherId);
    }
  }
}
</script>

<style scoped>
.date {
  display: block;
}
.switch-container {
  display: flex;
  align-items: center;
  margin-bottom: 10px; /* Lub inny odstęp pomiędzy sliderami */
}

.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
  margin-bottom: 10px;
}

.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #2196F3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
  display: inline-block;
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
/* Styluj etykiety */
.label {
  display: inline-block;
  margin-left: 20px;
}
</style>
