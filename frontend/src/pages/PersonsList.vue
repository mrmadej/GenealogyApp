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
          <q-btn
            icon="edit"
            @click="renderTree(props.row)"
            color="negative"
          />
          <q-btn
            icon="delete"
            @click="onDelete(props.row.id)"
            color="negative"
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
  emits: ['editPerson', 'renderTree'],
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
      console.log("W edit");
      //this.$router.push({ name: 'AddNewPerson', params: {person: person }})
      //this.$router.push({ path: 'AddNewPerson' });
      this.$emit('editPerson', person);
    },
    renderTree(person) {
      this.$emit("renderTree", person);
    },
    onDelete(id) {
      axios.delete(`${apiUrl}/deletePerson`, {
        params: {
          id: id
        }
      })
        .then(response => {
          console.log(response)
          this.fetchPeople();
        })
        .then(error => {
          console.log(error)
        });

    }
  }
};
</script>

<style scoped>

</style>
