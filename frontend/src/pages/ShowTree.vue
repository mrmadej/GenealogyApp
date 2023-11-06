<template>
  <p>JESTEM DRZEWEM</p>
</template>

<script>
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
