<template>
  <div class="q-pa-md">
    <q-table
      title="Persons"
      :rows="rows"
      :columns="columns"
      row-key="name"
    />
  </div>
</template>

<script>
export default {
  props: ['rows', 'columns']
};
</script>

<style scoped>

</style>
