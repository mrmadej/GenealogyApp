<template>
  <div class="q-pa-md">
    <q-table
      title="Persons"
      :rows="rows"
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
export default {
  props: ['rows', 'columns'],
  methods: {
    onEdit(row) {
      // Obsługa zdarzenia edycji - możesz otworzyć panel edycji dla tego wiersza
      console.log('Edytuj', row);
    },
  }
};
</script>

<style scoped>

</style>
