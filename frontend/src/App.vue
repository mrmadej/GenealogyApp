<template>
  <div class="q-pa-md q-gutter-y-sm">
    <q-toolbar class="text-primary">
      <q-btn flat round dense icon="menu" />
      <q-toolbar-title>
        Toolbar
      </q-toolbar-title>
      <q-btn flat round dense @click="addPersonPanelChanger">Add Person </q-btn>
      <q-btn flat round dense @click="personsListPanelChanger">Show Persons </q-btn>
      <q-btn flat round dense icon="more_vert" />
    </q-toolbar>
  </div>

  <persons-list
    v-if="personsListFlag"
    :columns="columns"
    :rows="people"
  ></persons-list>

  <add-new-person
    v-if="addPersonFlag"
    :persons-list-flag="this.personsListFlag"
  ></add-new-person>
</template>


<script>
import axios from'axios';
import PersonsList from "components/PersonsList.vue";
import AddNewPerson from "components/AddNewPerson.vue";

const apiUrl = 'http://localhost:8081/Main'; //process.env.VUE_APP_API_URL;

export default {
  components: {
    PersonsList,
    AddNewPerson
  },
  data() {
    return {
       columns : [
        { name: 'id', label: 'Id', field: 'id' },
        { name: 'name', label: 'Imie', field: 'name' },
        { name: 'surname', label: 'Nazwisko', field: 'surname' },
        { name: 'motherName', label: 'Imię Matki', field: row => row.mother ? row.mother.name : '-' },
        { name: 'motherName', label: 'Nazwisko Matki', field: row => row.mother ? row.mother.surname : '-' },
        { name: 'fatherName', label: 'Imię Ojca', field: row => row.father ? row.father.name : '-' },
        { name: 'birth', label: 'Data urodzenia', field: row => row.birth ? row.birth : '-'  },
        { name: 'death', label: 'Data zgonu', field: row => row.death ? row.death : '-'  },
        { name: 'action', label: 'Action'},

       ],
      people: [],
      personsListFlag: true,
      addPersonFlag: false,
    };
  },
  created() {
    console.log("Jestem");
    this.fetchPeople();
  },
  methods: {
    fetchPeople() {
      axios.get(`${apiUrl}/people`)
        .then(response => {
          console.log("Data", response);
          this.people = response.data;
        })
        .catch(error => {
          console.log('Error fetching people:', error);
        });
    },
    disableFlags()
    {
      this.addPersonFlag = false;
      this.personsListFlag = false;
    },
    async addPersonPanelChanger()
    {
      this.disableFlags();
      this.addPersonFlag = true;
    },
    personsListPanelChanger()
    {
      this.fetchPeople();
      this.disableFlags();
      this.personsListFlag = true;
    },
  }
};
</script>
