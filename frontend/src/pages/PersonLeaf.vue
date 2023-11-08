<template>
  <div class="person-details">
    <h3 class="person-name">{{ fullName }}</h3>
    <p v-if="birthDate" class="person-birth">ur. {{birthDate}}</p>
    <p v-if="deathDate" class="person-death">zm. {{deathDate}}</p>
  </div>
</template>

<script>

export default {
  computed: {
    fullName() {
      return `${this.name} ${this.surname}`;
    },
    birthDate() {
      return this.birth ? new Date(this.birth).toLocaleDateString() : '-';
    },
    deathDate() {
      return this.death ? new Date(this.death).toLocaleDateString() : '-';
    }
  },
  props: {
    name: String,
    surname: String,
    birth: Date,
    death: Date
  }
}

</script>

<style scoped>
.person-details {
  font-family: Arial, sans-serif;
  background-color: #f7f7f7;
  border: 1px solid #ddd;
  padding: 10px;
  margin: 10px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
}

.person-name {
  font-size: 1.5rem;
  margin: 0;
  color: #333;
}

.person-details p {
  margin: 5px 0;
}

.person-birth {
  font-style: italic;
  color: #666;
}

.person-death {
  font-style: italic;
  color: red;
}
</style>
