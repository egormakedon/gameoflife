let arr = [[0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0], [0, 0, 1, 1, 1, 0], [0, 1, 1, 1, 0, 0], [0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0]];

console.log(arr);
render();
setInterval(run, 1000);

function render() {
  for (let i = 0; i < arr.length; i++) {
    for (let j = 0; j < arr[i].length; j++) {
      let el = document.getElementById(`${i}${j}`);
      el.classList.remove('black');

      if (arr[i][j] === 1) {
        el.classList.add('black');
      }
    }
  }
}

function run() {
  arr = gameOfLife(arr);
  console.log(arr);
  render();
}

function gameOfLife(arr) {
  let newArr = [];

  for (let i = 0; i < arr.length; i++) {
    newArr.push([]);

    for (let j = 0; j < arr[i].length; j++) {
      let value = isAlive(arr, i, j) ? 1 : 0;
      newArr[i].push(value);
    }
  }

  return newArr;
}

function isAlive(arr, i, j) {
  let value = arr[i][j];

  let aliveCount = 0;
  for (let ki = i - 1; ki <= i + 1; ki++) {
    for (let kj = j - 1; kj <= j + 1; kj++) {
      if (ki >= 0 && ki < arr.length && kj >= 0 && kj < arr[ki].length) {
        if (arr[ki][kj] === 1 && (ki !== i || kj !== j)) {
          ++aliveCount;
        }
      }
    }
  }

  return (value === 1 && (aliveCount === 2 || aliveCount === 3)) || (value === 0 && aliveCount === 3);
}
