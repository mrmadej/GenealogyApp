<template>
  <div class="q-pa-md">
    <q-table
      title="Persons"
      :rows="people"
      :columns="columns"
      row-key="name"
    >
      <template v-slot:body-cell-action="props">
        <q-td :props="props">
          <q-btn
            icon="edit"
            @click="onEdit(props.row)"
            color="primary"
          />
        </q-td>
      </template>
    </q-table>
  </div>
</template>

<script>
import axios from "axios";
const apiUrl = 'http://localhost:8081/Main'; //process.env.VUE_APP_API_URL;
export default {
  emits: ['editPerson'],
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
      personToEdit: {}
    };
  },
  provide() {
    return {
      person: this.personToEdit
    };
  },
  created() {
    console.log("tu jestem w PersonsList");
  },
  mounted() {
    this.fetchPeople();
  },
  methods: {
    fetchPeople() {
      console.log("tu jestem w fetch people");
      axios.get(`${apiUrl}/people`)
        .then(response => {
          console.log("Data", response);
          this.people = response.data;
        })
        .catch(error => {
          console.log('Error fetching people:', error);
        });
    },
    onEdit(person) {
      // this.personToEdit = row;

      //this.$router.push({ name: 'AddNewPerson', params: {person: person }})
      //this.$router.push({ path: 'AddNewPerson' });
      this.$emit('editPerson', person);
    },
  }
};
</script>

<style scoped>

</style>
