<template>
  <person-leaf
    :name="person.name"
    :surname="person.surname"
    :birth="person.birth"
    :death="person.death"
  ></person-leaf>
  <div v-if="person.father">
    <show-tree :person="person.father"></show-tree>
  </div>
  <div v-if="person.mother">
    <show-tree :person="person.mother"></show-tree>
  </div>

</template>

<script>

import PersonLeaf from "pages/PersonLeaf.vue";

export default {
  components: {
    PersonLeaf
  },
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
    console.log("Znajdowanie glebokosci");
    this.treeDepth(this.person, this.depth);
    console.log("Tree depth: ")
    console.log(this.depth);
  },
  data() {
    return {
      depth: 0,
    };
  },
  methods: {
    treeDepth(rootPerson, currentDepth) {
      if(rootPerson.father != null || rootPerson.mother != null) {
        currentDepth++;
        if(currentDepth > this.depth) {
          this.depth = currentDepth;
        }
      }
      if(rootPerson.father != null) {
        this.treeDepth(rootPerson.father, currentDepth);
      }
      if(rootPerson.mother != null) {
        this.treeDepth(rootPerson.mother, currentDepth);
      }
    }
  }
}
</script>

<style scoped>

</style>
