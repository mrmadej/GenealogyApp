<template>
<!--  <q-layout>
    <q-page-container>
      <router-view></router-view>
    </q-page-container>
  </q-layout>
-->
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
    @editPerson="editPerson"
    @renderTree="renderTree"
  ></persons-list>

  <add-new-person
    v-if="addPersonFlag"
    :person="this.person"
    @changeToPersonList="personsListPanelChanger"
  ></add-new-person>
  <show-tree
    v-if="showTreeFlag"
    :person="this.person"
  ></show-tree>
</template>


<script>
import axios from'axios';
import PersonsList from "pages/PersonsList.vue";
import AddNewPerson from "pages/AddNewPerson.vue";
import ShowTree from "pages/ShowTree.vue";


export default {
  components: {
    PersonsList,
    AddNewPerson,
    ShowTree
  },
  // provide: {
  //   personsListFlag: this.personsListFlag
  // },
  data() {
    return {
      personsListFlag: true,
      addPersonFlag: false,
      showTreeFlag: false,
      person: null,
    };
  },

  methods: {
    disableFlags()
    {
      this.addPersonFlag = false;
      this.personsListFlag = false;
    },
    async addPersonPanelChanger()
    {
      this.disableFlags();
      this.addPersonFlag = true;
      console.log("dupa");
      console.log('W panelChanger: ');
      console.log(this.person);
    },
    personsListPanelChanger()
    {
      this.person = null;
      // this.fetchPeople();
      this.disableFlags();
      this.personsListFlag = true;
    },
    showTreePanelChanger() {
      this.disableFlags();
      this.showTreeFlag = true;
    },
    editPerson(person)
    {
      this.person = person;
      this.addPersonPanelChanger();
      console.log('W editPerson: ');
      console.log(this.person);
    },
    renderTree(person) {
      this.person = person;
      this.showTreePanelChanger();
    }
  }
};
</script>
