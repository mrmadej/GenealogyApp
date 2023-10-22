<template>
<!--  <div class="q-pa-md q-gutter-sm">-->
<!--    <q-btn style="background: #FF0080; color: white" label="Fuchsia" />-->
<!--    <q-btn flat style="color: #FF0080" label="Fuchsia Flat" />-->
<!--    <q-btn style="background: goldenrod; color: white" label="Goldenrod" />-->
<!--    <q-btn outline style="color: goldenrod;" label="Goldenrod" />-->
<!--    <q-btn color="grey-4" text-color="purple" glossy unelevated icon="camera_enhance" label="Purple text" />-->
<!--  </div>-->

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
<!--  <div class="q-pa-md">-->
<!--    <q-table-->
<!--      title="Persons"-->
<!--      :rows="people"-->
<!--      :columns="kolumny"-->
<!--      row-key="name"-->
<!--    />-->
<!--  </div>-->
  <persons-list
    v-if="personsListFlag"
    :columns="kolumny"
    :rows="people"
  ></persons-list>
  <add-new-person
    v-if="addPersonFlag"
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
       kolumny : [
        { name: 'id', label: 'Id', field: 'id' },
        { name: 'name', label: 'Imie', field: 'name' },
        { name: 'surname', label: 'Nazwisko', field: 'surname' },
        { name: 'motherName', label: 'Imię Matki', field: row => row.mother ? row.mother.name : '-' },
        { name: 'motherName', label: 'Nazwisko Matki', field: row => row.mother ? row.mother.surname : '-' },
        { name: 'fatherName', label: 'Imię Ojca', field: row => row.father ? row.father.name : '-' },
        { name: 'birth', label: 'Data urodzenia', field: row => row.birth ? row.birth : '-'  },
        { name: 'death', label: 'Data zgonu', field: row => row.death ? row.death : '-'  },
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
    addPersonPanelChanger()
    {
      this.disableFlags();
      this.addPersonFlag = true;
    },
    personsListPanelChanger()
    {
      this.disableFlags();
      this.personsListFlag = true;
    }

  }
};
</script>
